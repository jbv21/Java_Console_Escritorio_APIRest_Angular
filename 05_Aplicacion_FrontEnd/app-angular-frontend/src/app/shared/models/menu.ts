export interface Menu {
  label: string;
  route?: string;
  children?: Menu[];
  action?: () => void;
}
